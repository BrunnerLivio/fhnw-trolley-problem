<script lang="ts">
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import CategorySelect from "../lib/Editor/CategorySelect.svelte";
    import Editor from "../lib/Editor/Editor.svelte";
    import QuestionInput from "../lib/Editor/QuestionInput.svelte";
    import Loading from "../lib/Ui/Loading.svelte";
    import type { Problem } from "../models/Problem";
    import type { ProblemCreate } from "../models/ProblemCreate";
    import type { Victim } from "../models/Victim";
    import { categoryService } from "../services/CategoryService";
    import { victimService } from "../services/VictimService";

    const initialProblem: ProblemCreate = {
        question: "",
        leftVictims: [],
        rightVictims: [],
        categoryId: 0,
        leftLabel: null,
        rightLabel: null,
    };

    const categoriesPromise = categoryService.list();
    const victimsPromise = victimService.list();

    const loadingPromise = Promise.all([categoriesPromise, victimsPromise]);

    $: problem = initialProblem;
</script>

<div class="flex flex-col w-full gap-8">
    {#await loadingPromise}
        <Loading />
    {:then [categories, allVictims]}
        {#if problem}
            <pre>
                {JSON.stringify(problem, null, 2)}
            </pre>
            <Editor {categories} {allVictims} bind:problem>
                <Diagram
                    leftVictims={problem.leftVictims}
                    rightVictims={problem.rightVictims}
                    leftLabel={problem.leftLabel}
                    rightLabel={problem.rightLabel}
                />
            </Editor>
        {/if}
    {/await}
</div>
