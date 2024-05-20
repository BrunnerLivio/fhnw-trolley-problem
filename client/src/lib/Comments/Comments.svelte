<script lang="ts">
    import { problemService } from "../../services/ProblemService";
    import Loading from "../Ui/Loading.svelte";

    export let problemId: number;

    const commentsPromise = problemService.comments(problemId);

</script>
{#await commentsPromise}
    <Loading />
{:then comments}
    <div>
        <p>Comments</p>
        {#each comments as comment}
            <div>
                <p>{comment.text}</p>
                <p>{comment.author}</p>
            </div>
        {/each}
    </div>
{/await}
