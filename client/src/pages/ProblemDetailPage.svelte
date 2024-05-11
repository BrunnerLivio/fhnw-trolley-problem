<script lang="ts">
    // Workaround for https://github.com/ItalyPaleAle/svelte-spa-router/issues/274
    import ProblemDetail from "../lib/Problem/Detail.svelte";
    export let params: { categoryName?: string; problemId?: number } = {};
</script>

{#key params.problemId}
    <ProblemDetail {params} />
{/key}
