<script lang="ts">
    import CategoryDetail from "./pages/CategoryDetail.svelte";
    import Create from "./pages/Create.svelte";
    import ProblemDetailPage from "./pages/ProblemDetailPage.svelte";
    import Home from "./pages/Home.svelte";
    import NotFound from "./pages/NotFound.svelte";
    import Router from "svelte-spa-router";

    const routes = {
        "/": Home,
        "/create": Create,
        "/category/:categoryName": CategoryDetail,
        "/category/:categoryName/problem/:problemId": ProblemDetailPage,
        "*": NotFound,
    };
</script>

<Router {routes} />
