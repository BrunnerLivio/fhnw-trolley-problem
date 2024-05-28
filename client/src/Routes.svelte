<script lang="ts">
    import CategoryDetail from "./pages/CategoryDetail.svelte";
    import Create from "./pages/Create.svelte";
    import ScenarioDetailPage from "./pages/ScenarioDetailPage.svelte";
    import Home from "./pages/Home.svelte";
    import NotFound from "./pages/NotFound.svelte";
    import Router from "svelte-spa-router";
    import About from "./pages/About.svelte";

    const routes = {
        "/": Home,
        "/create": Create,
        "/about": About,
        "/category/:categoryName": CategoryDetail,
        "/category/:categoryName/scenario/:scenarioId": ScenarioDetailPage,
        "*": NotFound,
    };
</script>

<Router {routes} />
