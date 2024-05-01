<script lang="ts">
    import type { ComponentProps } from "svelte";
    import { problemService } from "../services/ProblemService";
    import { Position } from "../models/Position";

    import VoteSummary from "../lib/VoteSummary.svelte";
    import Button from "../lib/Button.svelte";
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import Loading from "../lib/Loading.svelte";

    const problemPromise = problemService.random();
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

<div class="max-w-screen-lg">
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
                <Button
                    on:click={() => handleVote(problem.id, Position.LEFT)}
                    intent="secondary"
                >
                    Pull the lever
                </Button>
                <Button
                    on:click={() => handleVote(problem.id, Position.RIGHT)}
                    intent="secondary"
                >
                    Do nothing
                </Button>
            </div>
        {/if}
        {#if votes && chosenOption}
            <VoteSummary {votes} {chosenOption} />
        {/if}
    {:catch error}
        <p>{error.message}</p>
    {/await}
</div>
