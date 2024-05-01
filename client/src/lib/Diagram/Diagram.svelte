<script lang="ts">
    import Track from "../../assets/track.svg";
    import Splat from "../../assets/splat.svg";
    import { Position } from "../../models/Position";
    import type { Victim } from "../../models/Victim";

    import You from "./You.svelte";
    import Trolley from "./Trolley.svelte";
    import VictimCmp from "./Victim.svelte";
    import Label from "./Label.svelte";

    export let leftVictims: Victim[] = [];
    export let rightVictims: Victim[] = [];
    export let leftLabel: string | null;
    export let rightLabel: string | null;
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

<div class="w-full overflow-hidden" role="presentation">
    <div class="relative mt-32">
        <img src={Track} alt="Track" />
        <Trolley {chosenOption} />

        {#if !!leftLabel}
            <div
                class="absolute right-[25%] top-[0%] z-10 max-w-56 -translate-y-1/2"
            >
                <Label label={leftLabel} />
            </div>
        {/if}

        <div class="absolute max-w-[33%] flex top-[0%] left-3/4">
            {#if !leftDead}
                {#each leftVictims as victim, index}
                    <VictimCmp yOffsetMultiplier={5} {victim} {index} />
                {/each}
            {/if}
            {#if leftDead && leftVictims.length > 0}
                <img src={Splat} alt="Splat" class="max-w-[60%] w-full top-0" />
            {/if}
        </div>

        {#if !!rightLabel}
            <div
                class="absolute right-[43%] top-[42%] z-10 max-w-56 -translate-y-1/2"
            >
                <Label label={rightLabel} />
            </div>
        {/if}

        <div class="absolute max-w-[32%] flex top-[38%] left-[55%]">
            {#if !rightDead}
                {#each rightVictims as victim, index}
                    <VictimCmp yOffsetMultiplier={10} {victim} {index} />
                {/each}
            {/if}
            {#if rightDead && rightVictims.length > 0}
                <img
                    src={Splat}
                    alt="Splat"
                    class="max-w-[60%] translate-x-1/4 w-full top-0"
                />
            {/if}
        </div>

        <You {chosenOption} />
    </div>
</div>
