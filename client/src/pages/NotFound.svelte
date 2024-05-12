<script lang="ts">
    import Button from "../lib/Ui/Button.svelte";
    import EndlessYouPullAnimation from "../lib/Ui/EndlessYouPullAnimation.svelte";
</script>

<div class="flex flex-col items-center flex-1 h-full gap-16 mt-16">
    <p class="text-3xl">404 - Not Found</p>
    <EndlessYouPullAnimation />
    <a href="/">
        <Button>Go Home</Button>
    </a>
</div>
