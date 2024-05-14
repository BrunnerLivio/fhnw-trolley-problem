<script lang="ts">
    import type { Category } from "../../models/Category";
    import type { Victim } from "../../models/Victim";
    import QuestionInput from "./QuestionInput.svelte";
    import VictimsEditor from "./VictimsEditor.svelte";
    import type { ProblemCreate } from "../../models/ProblemCreate";
    import CategorySelect from "./CategorySelect.svelte";
    import Collapse from "../Ui/Collapse.svelte";
    import Input from "./Input.svelte";

    export let problem: ProblemCreate;

    export let categories: Category[] = [];
    export let allVictims: Victim[] = [];

    const addVictim = (position: string, victim: Victim) => {
        if (position === "left") {
            problem.leftVictims = [...(problem.leftVictims || []), victim];
        } else {
            problem.rightVictims = [...(problem.rightVictims || []), victim];
        }
    };
</script>

<div class="flex flex-col flex-1 h-full">
    <div class="flex flex-col items-center justify-center w-full">
        <div class="flex flex-col w-full max-w-screen-lg gap-4 pt-4 pb-8">
            <QuestionInput bind:problem />
            <CategorySelect bind:value={problem.categoryId} {categories} />
        </div>
    </div>
    <div class="flex flex-1">
        <div
            class="flex flex-col w-full max-w-xl gap-4 p-8 border-t-2 border-b-2 border-r-2 border-primary"
        >
            <span class="text-2xl font-bold">Editor</span>
            <Collapse title="Left Track">
                <Input bind:value={problem.leftLabel} label="Label" />
                <VictimsEditor
                    {allVictims}
                    on:add={(e) => addVictim("left", e.detail)}
                />
            </Collapse>
            <Collapse title="Right Track">
                <Input bind:value={problem.rightLabel} label="Label" />
                <VictimsEditor
                    {allVictims}
                    on:add={(e) => addVictim("right", e.detail)}
                />
            </Collapse>
        </div>
        <div class="flex-1 p-8 border-t-2 border-b-2 border-primary">
            <span class="mb-4 text-2xl font-bold">Preview</span>
            <div class="flex items-center justify-center w-full">
                <div class="w-full max-w-3xl">
                    <slot />
                </div>
            </div>
        </div>
    </div>
</div>
