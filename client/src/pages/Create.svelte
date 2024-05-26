<script lang="ts">
    import { push } from "svelte-spa-router";
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import Editor from "../lib/Editor/Editor.svelte";
    import Loading from "../lib/Ui/Loading.svelte";
    import type { ProblemCreate } from "../models/ProblemCreate";
    import { categoryService } from "../services/CategoryService";
    import { problemService } from "../services/ProblemService";
    import { victimService } from "../services/VictimService";
    import { toastService } from "../services/toastService";

    const initialProblem: ProblemCreate = {
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
            problem.leftVictims = problem.leftVictims.filter(
                (_, i) => i !== index,
            );
        } else {
            problem.rightVictims = problem.rightVictims.filter(
                (_, i) => i !== index,
            );
        }
    };

    const handleSubmit = async (problem: ProblemCreate) => {
        await problemService.create(problem);
        toastService.success("Problem created successfully");
        push("/");
    };

    $: problem = initialProblem;
</script>

<div class="flex flex-col flex-1 w-full gap-8">
    {#await loadingPromise}
        <Loading />
    {:then [categories, allVictims]}
        {#if problem}
            <Editor
                on:submit={(e) => handleSubmit(e.detail)}
                {categories}
                {allVictims}
                bind:problem
            >
                <Diagram
                    leftVictims={problem.leftVictims}
                    rightVictims={problem.rightVictims}
                    leftLabel={problem.leftLabel}
                    rightLabel={problem.rightLabel}
                    deletable
                    on:delete={(e) => handleDeleteVictim(e.detail)}
                />
            </Editor>
        {/if}
    {/await}
</div>
