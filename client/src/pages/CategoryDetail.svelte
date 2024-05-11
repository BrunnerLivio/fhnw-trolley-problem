<script lang="ts">
    import { link, replace } from "svelte-spa-router";
    import Loading from "../lib/Loading.svelte";
    import { categoryService } from "../services/CategoryService";
    import Button from "../lib/Button.svelte";
    import NotFound from "../lib/Problem/NotFound.svelte";

    export let params: { categoryName?: string } = {};

    const randomProblemPromise = categoryService
        .randomProblem(params.categoryName!)
        .then((problem) => {
            replace(
                `/category/${params.categoryName}/problem/${problem.problemId}`,
            );
        });
</script>

<div class="flex flex-col flex-1 w-full h-full max-w-screen-lg">
    {#await randomProblemPromise}
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
