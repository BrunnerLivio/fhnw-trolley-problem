<script lang="ts">
    import Track from "./assets/track.svg";
    import Trolley from "./assets/trolley.svg";
    import You from "./assets/you.svg";

    import Navigation from "./lib/Navigation.svelte";
    import { problemService, Position } from "./services/ProblemService";
    import VoteSummary from "./lib/VoteSummary.svelte";
    import type { ComponentProps } from "svelte";
    import Button from "./lib/Button.svelte";

    const problemPromise = problemService.random();
    let votes: ComponentProps<VoteSummary>["votes"] | null = null;
    let chosenPosition: Position | null = null;

    const handleVote = async (problemId: number, position: Position) => {
        const problem = await problemService.vote(problemId, position);
        const totalVotes = problem.leftVotes + problem.rightVotes;
        chosenPosition = position;
        votes = {
            [Position.LEFT]: (problem.leftVotes / totalVotes) * 100,
            [Position.RIGHT]: (problem.rightVotes / totalVotes) * 100,
            total: totalVotes,
        };
    };
</script>

<main class="flex flex-col items-center gap-4 p-8">
    <Navigation />

    <div class="max-w-screen-lg">
        {#await problemPromise}
            <p>Loading...</p>
        {:then problem}
            <p class="text-2xl">{problem.question}</p>
            <div class="w-full">
                <div class="relative mt-32">
                    <img src={Track} alt="Track" />
                    <img
                        class="max-w-[21%] top-0 left-0 absolute -translate-y-1/2"
                        src={Trolley}
                        alt="Trolley"
                    />

                    {#each problem.leftVictims as victim, index}
                        <img
                            class="absolute max-w-[10%] top-0"
                            style="left: {75 +
                                index * 3}%; transform: translateY({index *
                                7}%)"
                            src={victim.imageUrl}
                            alt={victim.name}
                        />
                    {/each}
                    {#each problem.rightVictims as victim, index}
                        <img
                            class="absolute max-w-[10%] top-1/2"
                            style="left: {55 +
                                index * 3}%; transform: translateY({-25 +
                                index * 7}%)"
                            src={victim.imageUrl}
                            alt={victim.name}
                        />
                    {/each}
                    <img
                        src={You}
                        alt="You"
                        class="absolute max-w-[10%] bottom-0 left-1/4 transform -translate-x-1/2 -translate-y-1/2"
                    />
                </div>
            </div>
            {#if !chosenPosition}
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
            {#if votes && chosenPosition}
                <VoteSummary {votes} {chosenPosition} />
            {/if}
        {:catch error}
            <p>{error.message}</p>
        {/await}
    </div>
</main>
