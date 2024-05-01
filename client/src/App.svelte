<script lang="ts">
    import Routes from "./Routes.svelte";
    import Navigation from "./lib/Navigation.svelte";
</script>

<main class="flex flex-col items-center h-full gap-4 min-h-svh">
    <Navigation />

    <Routes />
</main>
