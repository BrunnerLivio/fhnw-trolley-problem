<script lang="ts">
    import { createEventDispatcher } from "svelte";
    import Input from "../Editor/Input.svelte";
    import Button from "../Ui/Button.svelte";
    import type { CommentCreate } from "../../models/CommentCreate";

    const initialValues: CommentCreate = {
        text: "",
        author: "",
    };

    export let comment: CommentCreate = initialValues;
    export let disabledAuthor = false;

    const dispatch = createEventDispatcher<{ submit: CommentCreate }>();

    const handleSubmit = () => {
        dispatch("submit", comment);
    };
</script>

<form
    on:submit|preventDefault={() => handleSubmit()}
    class="flex flex-col items-center flex-grow w-full gap-4 md:flex-row"
>
    <Input
        label="Author"
        disabled={disabledAuthor}
        required
        bind:value={comment.author}
    />
    <Input label="Comment" required bind:value={comment.text} />
    <Button type="submit">Submit</Button>
</form>
