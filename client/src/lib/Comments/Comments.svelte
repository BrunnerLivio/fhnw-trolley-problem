<script lang="ts">
    import { problemService } from "../../services/ProblemService";
    import Loading from "../Ui/Loading.svelte";
    import Comment from "./Comment.svelte";

    export let problemId: number;

    const commentsPromise = problemService.comments(problemId);
</script>

{#await commentsPromise}
    <Loading />
{:then comments}
    <div class="flex flex-col h-full gap-4 overflow-y-auto max-h-80">
        {#each comments as comment}
            <Comment {comment} />
        {/each}
    </div>
{/await}
