<script lang="ts">
    import { Directional } from "../../models/Directional";
    import PieChart from "../Ui/PieChart.svelte";
    import { slide } from "svelte/transition";
    import { quintOut } from "svelte/easing";

    type Votes = {
        [Directional.LEFT]: number;
        [Directional.RIGHT]: number;
        total: number;
    };

    export let votes: Votes;
    export let chosenOption: Directional;

    $: otherPosition =
        chosenOption === Directional.LEFT
            ? Directional.RIGHT
            : Directional.LEFT;
    $: chosenVotesPercentage = votes[chosenOption].toFixed(2);
    $: otherVotesPercentage = votes[otherPosition].toFixed(2);
</script>

<div
    transition:slide={{
        delay: 1000,
        duration: 300,
        easing: quintOut,
        axis: "y",
    }}
    data-testid="vote-summary"
    class="fixed bottom-0 left-0 z-30 flex flex-col items-center w-full max-h-screen gap-16 p-8 overflow-y-auto text-2xl border-t-2 backdrop-blur-2xl border-t-primary"
>
    <div class="flex flex-col items-center justify-center gap-4 md:flex-row">
        <PieChart percentage={votes[chosenOption]} />
        <span data-testid="vote-summary-text">
            {chosenVotesPercentage}% of people agree with you, {otherVotesPercentage}%
            disagree ({votes.total} votes)
        </span>
        <slot name="button" />
    </div>
    <div class="w-full max-w-screen-lg">
        <slot name="comments" />
    </div>
</div>
