<script lang="ts">
    import Button from "../lib/Button.svelte";
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import Loading from "../lib/Loading.svelte";
    import type { ProblemCreate } from "../models/ProblemCreate";
    import type { Victim } from "../models/Victim";
    import { categoryService } from "../services/CategoryService";
    import { victimService } from "../services/VictimService";

    const initialProblem: ProblemCreate = {
        question: "",
        leftVictims: [],
        rightVictims: [],
        categoryId: 0,
    };
    export let problem: ProblemCreate | null;
    const categoriesPromise = categoryService.list();
    const victimsPromise = victimService.list();

    const loadingPromise = Promise.all([categoriesPromise, victimsPromise]);

    const adjustDynamicTextArea = (e: Event) => {
        const element = e.target as HTMLTextAreaElement;
        element.style.height = "5px";
        element.style.height = `${element.scrollHeight}px`;
    };

    let rightVictims: Victim[] = [];
    let leftVictims: Victim[] = [];

    $: problem = problem ?? initialProblem;
    $: placeholder = `A trolley is heading towards ${problem.rightVictims.length} people. You can pull the lever to divert it to the other track, killing ${problem.leftVictims.length} person instead. What do you do?`;
</script>

<div class="flex flex-col w-full max-w-screen-lg gap-8">
    {#await loadingPromise}
        <Loading />
    {:then [categories, allVictims]}
        <div class="flex w-full gap-2">
            <span class="text-2xl">Oh no!</span>
            <textarea
                bind:value={problem.question}
                on:input={(e) => adjustDynamicTextArea(e)}
                {placeholder}
                class="flex-1 text-2xl bg-transparent border-b-2 resize-none min-h-20 border-b-primary"
            />
        </div>

        <div class="flex gap-4">
            <span class="text-2xl">Category:</span>

            <select
                class="flex-1 text-2xl bg-transparent border-b-2 border-b-primary"
            >
                {#each categories as category}
                    <option value={category.id}>{category.name}</option>
                {/each}
            </select>
        </div>

        <div class="flex flex-col gap-4">
            <span class="text-2xl">Victims:</span>

            <div class="w-full overflow-x-auto">
                {#each allVictims as victim}
                    <div class="flex flex-col gap-4">
                        <img
                            src={victim.imageUrl}
                            alt={victim.name}
                            class="w-16 h-16"
                        />
                        <span class="text-2xl">{victim.name}</span>
                        <div class="flex gap-4">
                            <Button
                                on:click={() => {
                                    leftVictims = [...leftVictims, victim];
                                    problem.leftVictims = [...problem.leftVictims, victim.id];
                                }}
                            >
                                Add to left
                            </Button>
                            <Button
                                on:click={() => {
                                    rightVictims = [...rightVictims, victim];
                                    problem.rightVictims = [...problem.rightVictims, victim.id];
                                }}
                            >
                                Add to right
                            </Button>
                        </div>
                    </div>
                {/each}
            </div>
        </div>
        <Diagram {leftVictims} {rightVictims} chosenOption={null} />
    {/await}
</div>
