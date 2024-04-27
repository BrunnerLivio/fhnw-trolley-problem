<script lang="ts">
    import Create from "./pages/Create.svelte";
    import Home from "./pages/Home.svelte";
    import NotFound from "./pages/NotFound.svelte";
    import Router from "svelte-spa-router";

    const routes = {
        "/": Home,
        "/create": Create,
        "*": NotFound,
    };
</script>

<Router {routes} />
