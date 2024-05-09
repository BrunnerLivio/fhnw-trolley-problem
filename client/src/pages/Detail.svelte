<script lang="ts">
    import type { ComponentProps } from "svelte";
    import { problemService } from "../services/ProblemService";
    import { Position } from "../models/Position";

    import VoteSummary from "../lib/VoteSummary.svelte";
    import Button from "../lib/Button.svelte";
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import Loading from "../lib/Loading.svelte";
    import { link } from "svelte-spa-router";
    import { categoryService } from "../services/CategoryService";
    import EndlessYouPullAnimation from "../lib/EndlessYouPullAnimation.svelte";

    export let params: { name?: string } = {};

    const problemPromise = categoryService.randomProblem(params.name!);

    let votes: ComponentProps<VoteSummary>["votes"] | null = null;
    let chosenOption: Position | null = null;

    const handleVote = async (problemId: number, position: Position) => {
        const problem = await problemService.vote(problemId, position);
        const totalVotes = problem.leftVotes + problem.rightVotes;
        chosenOption = position;
        votes = {
            [Position.LEFT]: (problem.leftVotes / totalVotes) * 100,
            [Position.RIGHT]: (problem.rightVotes / totalVotes) * 100,
            total: totalVotes,
        };
    };
</script>

<div class="flex flex-col flex-1 w-full h-full max-w-screen-lg">
    {#await problemPromise}
        <Loading />
    {:then problem}
        <p class="text-2xl">{problem.question}</p>

        <Diagram
            leftVictims={problem.leftVictims}
            rightVictims={problem.rightVictims}
            leftLabel={problem.leftLabel}
            rightLabel={problem.rightLabel}
            {chosenOption}
        />

        {#if !chosenOption}
            <div class="flex justify-center gap-4 mt-8">
                <Button on:click={() => handleVote(problem.id, Position.LEFT)}>
                    Pull the lever
                </Button>
                <Button on:click={() => handleVote(problem.id, Position.RIGHT)}>
                    Do nothing
                </Button>
            </div>
        {/if}
        {#if votes && chosenOption}
            <VoteSummary {votes} {chosenOption} />
        {/if}
    {:catch error}
        <div
            class="flex flex-col items-center justify-center w-full gap-8 py-16"
        >
            {#if error.response.status === 404}
                <div
                    class="flex flex-col items-center justify-center gap-2 text-center"
                >
                    <h2 class="text-3xl">
                        You've reached the end of the track!
                    </h2>
                    <p class="text-xl text-gray-700">
                        There are no more problems in this category.
                    </p>
                </div>
                <EndlessYouPullAnimation class="w-40" />
            {:else}
                <h2 class="text-2xl">Something went wrong</h2>
            {/if}

            <a href="/" use:link>
                <Button>Go back</Button>
            </a>
        </div>
    {/await}
</div>
