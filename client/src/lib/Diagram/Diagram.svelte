<script lang="ts">
    import Track from "../../assets/track.svg";
    import Splat from "../../assets/splat.svg";
    import { Position } from "../../models/Position";
    import type { Victim } from "../../models/Victim";

    import You from "./You.svelte";
    import Trolley from "./Trolley.svelte";

    export let leftVictims: Victim[] = [];
    export let rightVictims: Victim[] = [];
    export let chosenOption: Position | null = null;

    let displaySplat: boolean;

    $: leftDead = displaySplat && chosenOption === Position.LEFT;
    $: rightDead = displaySplat && chosenOption === Position.RIGHT;

    $: {
        if (chosenOption !== null) {
            setTimeout(() => {
                displaySplat = true;
            }, 600);
        }
    }
</script>

<div class="w-full">
    <div class="relative pt-32 overflow-hidden">
        <img src={Track} alt="Track" />
        <Trolley {chosenOption} />

        {#if !leftDead}
            {#each leftVictims as victim, index}
                <img
                    class="absolute max-w-[10%] top-32"
                    style="left: {75 +
                        index * 3}%; transform: translateY({index * 7}%)"
                    src={victim.imageUrl}
                    alt={victim.name}
                />
            {/each}
        {/if}

        {#if leftDead && leftVictims.length > 0}
            <img
                src={Splat}
                alt="Splat"
                class="absolute max-w-[20%] top-[28%] left-3/4 transform translate-x-[-1rem] -translate-y-[30%]"
            />
        {/if}

        {#if !rightDead}
            {#each rightVictims as victim, index}
                <img
                    class="absolute max-w-[10%] top-[62%]"
                    style="left: {55 + index * 3}%; transform: translateY({-25 +
                        index * 7}%)"
                    src={victim.imageUrl}
                    alt={victim.name}
                />
            {/each}
        {/if}

        {#if rightDead && rightVictims.length > 0}
            <img
                src={Splat}
                alt="Splat"
                class="absolute max-w-[20%] top-[62%] left-3/4 transform -translate-x-full -translate-y-[30%]"
            />
        {/if}

        <You {chosenOption} />
    </div>
</div>
