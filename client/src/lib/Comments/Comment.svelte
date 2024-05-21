<script lang="ts">
    import { createEventDispatcher } from "svelte";
    import type { Comment } from "../../models/Comment";

    export let comment: Comment;
    const dispatch = createEventDispatcher<{
        edit: Comment;
        delete: Comment;
    }>();
</script>

<div class="flex gap-4">
    <div
        class="flex items-center justify-center w-12 h-12 bg-gray-300 rounded-full"
    >
        {comment.initials}
    </div>
    <div>
        <div class="flex gap-4">
            <strong style={`color: ${comment.authorColor}`}
                >{comment.author}</strong
            >
            {#if comment.createdByCurrentUser}
                <div class="flex gap-4">
                    <button
                        on:click={() => dispatch("edit", comment)}
                        class="flex-shrink px-4 text-xs bg-gray-100 rounded-lg"
                        >EDIT</button
                    >
                    <button
                        on:click={() => dispatch("delete", comment)}
                        class="flex-shrink px-4 text-xs bg-gray-100 rounded-lg"
                        >DELETE</button
                    >
                </div>
            {/if}
        </div>
        <p>{comment.text}</p>
    </div>
</div>
