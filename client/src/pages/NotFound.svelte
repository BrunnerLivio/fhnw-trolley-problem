<script lang="ts">
    import You from "../assets/you.svg";
    import YouPull from "../assets/you-pull.svg";
    import Button from "../lib/Button.svelte";

    $: pull = false;

    setInterval(() => {
        pull = !pull;
    }, 200);
</script>

<div class="flex flex-col items-center flex-1 h-full gap-16 mt-16">
    <p class="text-3xl">404 - Not Found</p>
    <img src={pull ? YouPull : You} alt="You" class="w-1/2" />
    <a href="/">
        <Button>Go Home</Button>
    </a>
</div>
