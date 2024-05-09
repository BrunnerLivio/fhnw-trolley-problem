<script lang="ts">
    import Create from "./pages/Create.svelte";
    import Detail from "./pages/Detail.svelte";
    import Home from "./pages/Home.svelte";
    import NotFound from "./pages/NotFound.svelte";
    import Router from "svelte-spa-router";

    const routes = {
        "/": Home,
        "/create": Create,
        "/category/:name": Detail,
        "*": NotFound,
    };
</script>

<Router {routes} />
