<script lang="ts">
    import { link } from "svelte-spa-router";
    import Button from "../Ui/Button.svelte";
    import EndlessYouPullAnimation from "../Ui/EndlessYouPullAnimation.svelte";

    export let title: string;
    export let description: string | undefined;
    export let reload = false;
</script>

<div class="flex flex-col items-center justify-center w-full gap-8 py-16">
    <div class="flex flex-col items-center justify-center gap-2 text-center">
        <h2 class="text-3xl">{title}</h2>
        {#if description}
            <p class="text-xl text-gray-700">{description}</p>
        {/if}
    </div>
    <EndlessYouPullAnimation class="w-40" />

    {#if reload}
        <Button on:click={() => window.location.reload()}>Reload</Button>
    {:else}
        <a href="/" use:link>
            <Button>Go back</Button>
        </a>
    {/if}
</div>
