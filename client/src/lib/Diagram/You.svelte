<script lang="ts">
    import { Position } from "../../services/ProblemService";

    import You from "../../assets/you.svg";
    import YouPull from "../../assets/you-pull.svg";

    export let chosenOption: Position | null = null;
</script>

{#if chosenOption === null || chosenOption === Position.RIGHT}
    <img
        src={You}
        alt="You"
        class="absolute max-w-[15%] bottom-0 left-1/4 transform -translate-x-1/2 -translate-y-1/2"
    />
{/if}

{#if chosenOption === Position.LEFT}
    <img
        src={YouPull}
        alt="You"
        class="absolute max-w-[15%] bottom-0 left-1/4 transform -translate-x-1/2 -translate-y-1/2"
    />
{/if}
