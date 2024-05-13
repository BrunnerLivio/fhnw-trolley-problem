<script lang="ts">
    export let value: string | null;
    export let label: string;
</script>

<div class="flex flex-col gap-4">
    <span>{label}</span>

    <input
        type="text"
        bind:value
        class="flex-1 bg-transparent border-b-2 border-b-primary"
    />
</div>
