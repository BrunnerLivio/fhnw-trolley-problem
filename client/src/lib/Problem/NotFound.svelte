<script>
    import { link } from "svelte-spa-router";
    import Button from "../Button.svelte";

    import EndlessYouPullAnimation from "../EndlessYouPullAnimation.svelte";
</script>

<div class="flex flex-col items-center justify-center w-full gap-8 py-16">
    <div class="flex flex-col items-center justify-center gap-2 text-center">
        <h2 class="text-3xl">You've reached the end of the track!</h2>
        <p class="text-xl text-gray-700">
            There are no more problems in this category.
        </p>
    </div>
    <EndlessYouPullAnimation class="w-40" />

    <a href="/" use:link>
        <Button>Go back</Button>
    </a>
</div>
