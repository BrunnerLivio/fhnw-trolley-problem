<script lang="ts">
    import type { Victim } from "../../models/Victim";
    import { createEventDispatcher } from "svelte";

    export let allVictims: Victim[] = [];
    const dispatch = createEventDispatcher<{
        add: Victim;
    }>();
</script>

<div class="flex flex-col gap-4">
    <span>Victims</span>
    <div class="flex flex-wrap gap-4">
        {#each allVictims as victim}
            <button on:click={() => dispatch("add", victim)}>
                <img
                    src={victim.imageUrl}
                    alt={victim.name}
                    class="w-16 h-16"
                />
            </button>
        {/each}
    </div>
</div>
