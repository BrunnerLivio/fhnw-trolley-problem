<script lang="ts">
    import Button from "../../lib/Button.svelte";
    import type { Victim } from "../../models/Victim";
    import type { Problem } from "../../models/Problem";

    export let problem: Partial<Problem>;

    export let allVictims: Victim[] = [];

    const addVictimToLeft = (victim: Victim) => {
        problem.leftVictims = [...(problem.leftVictims || []), victim];
    };

    const addVictimToRight = (victim: Victim) => {
        problem.rightVictims = [...(problem.rightVictims || []), victim];
    };
</script>

<div class="w-full overflow-x-auto">
    {#each allVictims as victim}
        <div class="flex flex-col gap-4">
            <img src={victim.imageUrl} alt={victim.name} class="w-16 h-16" />
            <span class="text-2xl">{victim.name}</span>
            <div class="flex gap-4">
                <Button on:click={() => addVictimToLeft(victim)}>
                    Add to left
                </Button>
                <Button on:click={() => addVictimToRight(victim)}>
                    Add to right
                </Button>
            </div>
        </div>
    {/each}
</div>
