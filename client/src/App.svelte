<script lang="ts">
    import Navigation from "./lib/Navigation.svelte";
    import {
        Position,
        TrackPosition,
        problemService,
    } from "./services/ProblemService";
    import Track from "./assets/track.svg";
    import Trolley from "./assets/trolley.svg";
    import You from "./assets/you.svg";

    const problemPromise = problemService.random();
</script>

<main class="flex flex-col items-center gap-4 p-8">
    <Navigation />

    <div class="max-w-screen-lg">
        {#await problemPromise}
            <p>Loading...</p>
        {:then problem}
            <p>{problem.question}</p>
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
            <div class="flex justify-center gap-4 mt-8">
                <button
                    on:click={() =>
                        problemService.vote(problem.id, Position.LEFT)}
                    class="p-4 text-xl border-2 rounded-lg border-primary hover:bg-primary hover:text-background"
                >
                    Pull the lever
                </button>
                <button
                    on:click={() =>
                        problemService.vote(problem.id, Position.RIGHT)}
                    class="p-4 text-xl border-2 rounded-lg border-primary hover:bg-primary hover:text-background"
                >
                    Do nothing
                </button>
            </div>
        {:catch error}
            <p>{error.message}</p>
        {/await}
    </div>
</main>
