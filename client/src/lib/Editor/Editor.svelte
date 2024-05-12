<script lang="ts">
    import type { Category } from "../../models/Category";
    import type { Victim } from "../../models/Victim";
    import QuestionInput from "./QuestionInput.svelte";
    import VictimsEditor from "./VictimsEditor.svelte";
    import type { ProblemCreate } from "../../models/ProblemCreate";
    import CategorySelect from "./CategorySelect.svelte";
    import Collapse from "../Ui/Collapse.svelte";
    import LabelsInput from "./LabelsInput.svelte";

    export let problem: ProblemCreate;

    export let categories: Category[] = [];
    export let allVictims: Victim[] = [];
</script>

<div class="flex flex-col items-center justify-center w-full">
    <div class="flex flex-col w-full max-w-screen-lg gap-4 pb-4">
        <QuestionInput bind:problem />
        <CategorySelect bind:value={problem.categoryId} {categories} />
    </div>
</div>
<div class="flex">
    <div
        class="flex flex-col w-full gap-4 p-8 border-t-2 border-b-2 border-r-2 max-w-96 border-primary"
    >
        <span class="mb-4 text-2xl font-bold">Editor</span>
        <Collapse title="Labels">
            <LabelsInput
                bind:leftLabel={problem.leftLabel}
                bind:rightLabel={problem.rightLabel}
            />
        </Collapse>
        <Collapse title="Victims">
            <VictimsEditor bind:problem {allVictims} />
        </Collapse>
    </div>
    <div class="flex-1 p-8 border-t-2 border-b-2 border-primary">
        <span class="mb-4 text-2xl font-bold">Preview</span>
        <slot />
    </div>
</div>
