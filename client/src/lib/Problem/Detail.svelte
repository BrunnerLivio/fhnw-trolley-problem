<script lang="ts">
    import type { ComponentProps } from "svelte";
    import { problemService } from "../../services/ProblemService";
    import { Position } from "../../models/Position";

    import VoteSummary from "./VoteSummary.svelte";
    import Button from "../Ui/Button.svelte";
    import Comments from "../Comments/Comments.svelte";
    import Diagram from "../Diagram/Diagram.svelte";
    import Loading from "../Ui/Loading.svelte";
    import { link } from "svelte-spa-router";
    import { categoryService } from "../../services/CategoryService";
    import NotFound from "./NotFound.svelte";
    import AlreadyVoted from "./AlreadyVoted.svelte";
    import SomethingWentWrong from "../Errors/SomethingWentWrong.svelte";

    export let params: { categoryName?: string; problemId?: number } = {};

    const problemPromise = categoryService.problemInCategory(
        params.categoryName!,
        params.problemId!,
    );

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
            <VoteSummary {votes} {chosenOption}>
                <div slot="button">
                    {#if problem.nextProblemId !== null}
                        <a
                            href={`/category/${params.categoryName}/problem/${problem.nextProblemId}`}
                            use:link
                        >
                            <Button>Next</Button>
                        </a>
                    {:else}
                        <a href="/" use:link>
                            <Button>Go back</Button>
                        </a>
                    {/if}
                </div>

                <Comments slot="comments" problemId={problem.id} />
            </VoteSummary>
        {/if}
    {:catch error}
        {#if error.response.status === 404}
            <NotFound />
        {:else if error.response.status === 403}
            <AlreadyVoted />
        {:else}
            <SomethingWentWrong />
        {/if}
    {/await}
</div>
