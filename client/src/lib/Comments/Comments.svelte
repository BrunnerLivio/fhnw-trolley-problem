<script lang="ts">
    import { onMount } from "svelte";
    import type { CommentCreate } from "../../models/CommentCreate";
    import { problemService } from "../../services/ProblemService";
    import Loading from "../Ui/Loading.svelte";
    import Comment from "./Comment.svelte";
    import CommentForm from "./CommentForm.svelte";
    import { commentService } from "../../services/CommentService";

    export let problemId: number;
    const initialValues: CommentCreate = { text: "", author: "" };
    const localStorageAuthor = localStorage.getItem("author");
    $: editingComment = {
        ...initialValues,
        author: localStorageAuthor || "",
    };
    $: commentsPromise = Promise.resolve<any>([]);
    $: disabledAuthor = !!localStorageAuthor;

    const fetchComments = () => {
        commentsPromise = problemService.comments(problemId);
    };

    const createOrUpdate = async (comment: CommentCreate) => {
        if (comment.id) {
            await commentService.update(comment.id, comment);
        } else {
            localStorage.setItem("author", comment.author);
            await problemService.createComment(problemId, comment);
        }
        editingComment = {
            ...initialValues,
            author: comment.author,
        };
        disabledAuthor = true;
        fetchComments();
    };

    const deleteComment = async (id: number) => {
        await commentService.remove(id);
        fetchComments();
    };

    onMount(() => {
        fetchComments();
    });
</script>

{#await commentsPromise}
    <Loading />
{:then comments}
    <div class="flex flex-col gap-8" data-testid="comments">
        <div
            class="flex flex-col h-full gap-4 overflow-y-auto max-h-[max(40vh,20rem)]"
        >
            {#if comments.length === 0}
                <p class="text-center">No comments yet</p>
            {:else}
                {#each comments as comment}
                    <Comment
                        on:delete={() => deleteComment(comment.id)}
                        on:edit={() => (editingComment = comment)}
                        {comment}
                    />
                {/each}
            {/if}
        </div>

        <CommentForm
            disabledAuthor={!!localStorageAuthor}
            comment={editingComment}
            on:submit={(e) => createOrUpdate(e.detail)}
        />
    </div>
{/await}
