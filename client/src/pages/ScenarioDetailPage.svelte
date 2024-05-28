<script lang="ts">
    // Workaround for https://github.com/ItalyPaleAle/svelte-spa-router/issues/274
    import ScenarioDetail from "../lib/Scenario/Detail.svelte";
    export let params: { categoryName?: string; scenarioId?: number } = {};
</script>

{#key params.scenarioId}
    <ScenarioDetail {params} />
{/key}
