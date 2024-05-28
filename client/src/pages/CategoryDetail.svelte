<script lang="ts">
    import { link, replace } from "svelte-spa-router";
    import Loading from "../lib/Ui/Loading.svelte";
    import { categoryService } from "../services/CategoryService";
    import Button from "../lib/Ui/Button.svelte";
    import NotFound from "../lib/Scenario/NotFound.svelte";

    export let params: { categoryName?: string } = {};

    const randomScenarioPromise = categoryService
        .randomScenario(params.categoryName!)
        .then((scenario) => {
            replace(
                `/category/${params.categoryName}/scenario/${scenario.scenarioId}`,
            );
        });
</script>

<div class="flex flex-col flex-1 w-full h-full max-w-screen-lg">
    {#await randomScenarioPromise}
        <Loading />
    {:catch error}
        {#if error.response.status === 404}
            <NotFound />
        {:else}
            <div
                class="flex flex-col items-center justify-center w-full gap-8 py-16"
            >
                <h2 class="text-2xl">Something went wrong</h2>

                <a href="/" use:link>
                    <Button>Go back</Button>
                </a>
            </div>
        {/if}
    {/await}
</div>
