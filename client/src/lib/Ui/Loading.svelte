<script lang="ts">
    import Trolley from "../../assets/trolley.svg";

    const dots = ["", ".", "..", "..."];
    let dotIndex = 0;

    setInterval(() => {
        dotIndex = (dotIndex + 1) % dots.length;
    }, 500);

    $: dots[dotIndex];
</script>

<div class="w-full py-8 overflow-hidden min-h-80">
    <div class="flex flex-col items-center justify-center gap-4 marquee">
        <span class="text-3xl w-36">Loading{dots[dotIndex]}</span>
        <img src={Trolley} alt="Trolley" class="w-48" />
    </div>
</div>

<style>
    .marquee {
        animation: marquee 5s linear infinite;
    }

    @keyframes marquee {
        0% {
            transform: translateX(-50%);
        }

        100% {
            transform: translateX(100%);
        }
    }
</style>
