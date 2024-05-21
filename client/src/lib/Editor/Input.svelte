<script lang="ts">
    export let value: string | null;
    export let label: string;
</script>

<div class="flex flex-col flex-grow gap-4">
    <span>{label}</span>

    <input
        {...$$restProps}
        type="text"
        bind:value
        class="flex-1 bg-transparent border-b-2 border-b-primary disabled:bg-gray-300"
    />
</div>
