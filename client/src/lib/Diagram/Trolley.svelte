<script lang="ts">
    import type { Directional } from "../../models/Directional";
    import Trolley from "../../assets/trolley.svg";

    export let chosenOption: Directional | null = null;

    $: trolleyAnimation =
        chosenOption === null
            ? ""
            : `animate-trolley-${chosenOption.toLowerCase()}`;
</script>

<img
    class={`max-w-[21%] z-30 top-[0%] left-0 absolute -translate-y-1/2 ${trolleyAnimation}`}
    src={Trolley}
    alt="Trolley"
/>

<style>
    .animate-trolley-left {
        animation: trolleyLeft 2s linear forwards;
    }

    .animate-trolley-right {
        animation: trolleyRight 2s linear forwards;
    }

    @keyframes trolleyRight {
        20% {
            transform: translateX(130%) translateY(10%) scale(1.2) rotate(0deg);
        }

        80% {
            transform: translateX(600%) translateY(220%) scale(1.5) rotate(5deg);
            opacity: 1;
        }

        100% {
            transform: translateX(700%) translateY(300%) scale(1.8) rotate(5deg);
            opacity: 0;
        }
    }

    @keyframes trolleyLeft {
        10% {
            transform: translateX(130%) translateY(0%) rotate(0deg);
        }

        20% {
            transform: translateX(200%) translateY(0%) rotate(-43deg);
        }

        25% {
            transform: translateX(220%) translateY(-20%) rotate(-48deg);
        }

        32% {
            transform: translateX(300%) translateY(-40%) scale(0.95)
                rotate(-20deg);
        }

        60% {
            transform: translateX(530%) translateY(12%) scale(0.9) rotate(0deg);
            opacity: 1;
        }

        100% {
            transform: translateX(580%) translateY(15%) scale(0.9) rotate(0deg);
            opacity: 0;
        }
    }
</style>
