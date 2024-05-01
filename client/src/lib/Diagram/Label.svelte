<script lang="ts">
    import Arrow from "../../assets/arrow.svg";

    export let label: string | null;
</script>

<span class="flex w-full gap-4 align-bottom">
    <span
        class="text-2xl font-bold text-right text-primary"
        style={`text-shadow: 2px 2px 0 rgb(255, 255, 255),
                             2px -2px 0 rgb(255, 255, 255),
                             -2px 2px 0 rgb(255, 255, 255),
                             -2px -2px 0 rgb(255, 255, 255)`}
    >
        {label}
    </span>
    <img
        style={`
        filter: drop-shadow(2px 2px 0px rgb(255,255,255))
                drop-shadow(-2px -2px 0 rgb(255,255,255))
                drop-shadow(2px -2px 0 rgb(255,255,255))
                drop-shadow(-2px 2px 0 rgb(255,255,255));`}
        width={30}
        class="bounce"
        src={Arrow}
        alt="Arrow"
    />
</span>

<style>
    .bounce {
        animation: bounce 1s infinite;
    }

    @keyframes bounce {
        0%,
        100% {
            transform: translateX(-5px);
        }
        50% {
            transform: translateX(5px);
        }
    }
</style>
