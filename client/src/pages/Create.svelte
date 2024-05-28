<script lang="ts">
    import { push } from "svelte-spa-router";
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import Editor from "../lib/Editor/Editor.svelte";
    import Loading from "../lib/Ui/Loading.svelte";
    import type { ScenarioCreate } from "../models/ScenarioCreate";
    import { categoryService } from "../services/CategoryService";
    import { scenarioService } from "../services/ScenarioService";
    import { victimService } from "../services/VictimService";
    import { toastService } from "../services/toastService";

    const initialScenario: ScenarioCreate = {
        question: "",
        leftVictims: [],
        rightVictims: [],
        categoryId: 1,
        leftLabel: "",
        rightLabel: "",
    };

    const categoriesPromise = categoryService.list();
    const victimsPromise = victimService.list();

    const loadingPromise = Promise.all([categoriesPromise, victimsPromise]);

    const handleDeleteVictim = ({
        directional,
        index,
    }: {
        directional: "LEFT" | "RIGHT";
        index: number;
    }) => {
        if (directional === "LEFT") {
            scenario.leftVictims = scenario.leftVictims.filter(
                (_, i) => i !== index,
            );
        } else {
            scenario.rightVictims = scenario.rightVictims.filter(
                (_, i) => i !== index,
            );
        }
    };

    const handleSubmit = async (scenario: ScenarioCreate) => {
        await scenarioService.create(scenario);
        toastService.success("Scenario created successfully");
        push("/");
    };

    $: scenario = initialScenario;
</script>

<div class="flex flex-col flex-1 w-full gap-8">
    {#await loadingPromise}
        <Loading />
    {:then [categories, allVictims]}
        {#if scenario}
            <Editor
                on:submit={(e) => handleSubmit(e.detail)}
                {categories}
                {allVictims}
                bind:scenario={scenario}
            >
                <Diagram
                    leftVictims={scenario.leftVictims}
                    rightVictims={scenario.rightVictims}
                    leftLabel={scenario.leftLabel}
                    rightLabel={scenario.rightLabel}
                    deletable
                    on:delete={(e) => handleDeleteVictim(e.detail)}
                />
            </Editor>
        {/if}
    {/await}
</div>
