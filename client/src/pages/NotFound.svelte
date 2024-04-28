<script lang="ts">
    import Button from "../lib/Button.svelte";
    import YouPullAnimation from "../lib/YouPullAnimation.svelte";

    $: pull = false;

    setInterval(() => {
        pull = !pull;
    }, 200);
</script>

<div class="flex flex-col items-center flex-1 h-full gap-16 mt-16">
    <p class="text-3xl">404 - Not Found</p>
    <YouPullAnimation />
    <a href="/">
        <Button>Go Home</Button>
    </a>
</div>
