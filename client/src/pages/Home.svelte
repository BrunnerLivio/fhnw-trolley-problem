<script lang="ts">
    import { link } from "svelte-spa-router";
    import Loading from "../lib/Ui/Loading.svelte";
    import { categoryService } from "../services/CategoryService";
    import Button from "../lib/Ui/Button.svelte";
    import ErrorWrapper from "../lib/Errors/ErrorWrapper.svelte";

    const categoryPromise = categoryService.list();
</script>

<div class="w-full max-w-screen-lg">
    {#await categoryPromise}
        <Loading />
    {:then categories}
        <div class="flex flex-col items-center w-full gap-4">
            <p class="text-2xl text-center">Browser trolley problems</p>

            <ul
                data-testid="category-overview"
                class="grid w-full grid-cols-1 gap-4 mt-8 md:grid-cols-2 lg:grid-cols-3 category-overview"
            >
                {#each categories as category}
                    <li
                        class="flex justify-center w-full category"
                        data-testid="category"
                    >
                        <a href={`/category/${category.name}`} use:link>
                            <Button>
                                {category.name}
                            </Button>
                        </a>
                    </li>
                {/each}
            </ul>

            <div class="relative w-full mt-8 -top-8">
                <!-- Divider-->
                <div class="flex justify-center w-full">
                    <div
                        class="flex justify-center w-full border-b-2 max-w-96 border-primary"
                    >
                        <p
                            class="px-4 text-2xl text-center translate-y-1/2 bg-background"
                        >
                            or create your own
                        </p>
                    </div>
                </div>
            </div>

            <a href="/create" use:link>
                <Button class="w-full mt-4">Create new</Button>
            </a>
        </div>
    {:catch}
        <ErrorWrapper
            title="Something went wrong"
            description="An unexpected error occurred. Please try again later."
            reload
        />
    {/await}
</div>

<style>
</style>
