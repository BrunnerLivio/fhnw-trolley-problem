<script lang="ts">
    import type { Comment } from "../../models/Comment";

    export let comment: Comment;
</script>

<div class="flex gap-4">
    <div
        class="flex items-center justify-center w-12 h-12 bg-gray-300 rounded-full"
    >
        {comment.initials}
    </div>
    <div>
        <strong style={`color: ${comment.authorColor}`}>{comment.author}</strong>
        <p>{comment.text}</p>
    </div>
</div>
