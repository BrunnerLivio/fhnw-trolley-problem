<script lang="ts">
    import You from "../../assets/you.svg";
    import YouPull from "../../assets/you-pull.svg";

    import { Directional } from "../../models/Directional";

    export let chosenOption: Directional | null = null;
</script>

{#if chosenOption === null || chosenOption === Directional.RIGHT}
    <img
        src={You}
        alt="You"
        class="absolute max-w-[15%] bottom-0 left-1/4 transform -translate-x-1/2 -translate-y-1/2"
    />
{/if}

{#if chosenOption === Directional.LEFT}
    <img
        src={YouPull}
        alt="You"
        class="absolute max-w-[15%] bottom-0 left-1/4 transform -translate-x-1/2 -translate-y-1/2"
    />
{/if}
