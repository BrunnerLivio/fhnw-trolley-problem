<script lang="ts">
    import { Position } from "../../models/Position";
    import PieChart from "../Ui/PieChart.svelte";

    type Votes = {
        [Position.LEFT]: number;
        [Position.RIGHT]: number;
        total: number;
    };

    export let votes: Votes;
    export let chosenOption: Position;

    $: otherPosition =
        chosenOption === Position.LEFT ? Position.RIGHT : Position.LEFT;
    $: chosenVotesPercentage = votes[chosenOption].toFixed(2);
    $: otherVotesPercentage = votes[otherPosition].toFixed(2);
</script>

<div
    class="fixed bottom-0 left-0 flex items-center justify-center w-full gap-4 p-8 text-2xl border-t-2 bg-background border-t-primary"
>
    <PieChart percentage={votes[chosenOption]} />
    <span>
        {chosenVotesPercentage}% of people agree with you, {otherVotesPercentage}%
        disagree ({votes.total} votes)
    </span>
    <slot />
</div>
