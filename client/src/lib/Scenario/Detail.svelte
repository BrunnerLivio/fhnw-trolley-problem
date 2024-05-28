<script lang="ts">
    import type { ComponentProps } from "svelte";
    import { scenarioService } from "../../services/ScenarioService";
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

    export let params: { categoryName?: string; scenarioId?: number } = {};

    const scenarioPromise = categoryService.scenarioInCategory(
        params.categoryName!,
        params.scenarioId!,
    );

    let votes: ComponentProps<VoteSummary>["votes"] | null = null;
    let chosenOption: Position | null = null;

    const handleVote = async (scenarioId: number, position: Position) => {
        const scenario = await scenarioService.vote(scenarioId, position);
        const totalVotes = scenario.leftVotes + scenario.rightVotes;
        chosenOption = position;
        votes = {
            [Position.LEFT]: (scenario.leftVotes / totalVotes) * 100,
            [Position.RIGHT]: (scenario.rightVotes / totalVotes) * 100,
            total: totalVotes,
        };
    };
</script>

<div
    class="flex flex-col flex-1 w-full h-full max-w-screen-lg px-4 pb-16"
    data-testid="scenario-detail"
>
    {#await scenarioPromise}
        <Loading />
    {:then scenario}
        <p class="text-2xl">{scenario.question}</p>

        <Diagram
            leftVictims={scenario.leftVictims}
            rightVictims={scenario.rightVictims}
            leftLabel={scenario.leftLabel}
            rightLabel={scenario.rightLabel}
            {chosenOption}
        />

        {#if !chosenOption}
            <div
                class="flex flex-col items-center justify-center gap-4 mt-8 md:flex-row"
            >
                <Button
                    on:click={() => handleVote(scenario.id, Position.LEFT)}
                    data-testid="pull-lever"
                >
                    Pull the lever
                </Button>
                <Button
                    on:click={() => handleVote(scenario.id, Position.RIGHT)}
                    data-testid="do-nothing"
                >
                    Do nothing
                </Button>
            </div>
        {/if}
        {#if votes && chosenOption}
            <VoteSummary {votes} {chosenOption}>
                <div slot="button">
                    {#if scenario.nextScenarioId !== null}
                        <a
                            href={`/category/${params.categoryName}/scenario/${scenario.nextScenarioId}`}
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

                <Comments slot="comments" scenarioId={scenario.id} />
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
