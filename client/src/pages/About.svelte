<script lang="ts">
    import Diagram from "../lib/Diagram/Diagram.svelte";
    import type { Victim } from "../models/Victim";
    import EndlessYouPullAnimation from "../lib/Ui/EndlessYouPullAnimation.svelte";

    const person: Victim = {
        id: 1,
        name: "Person",
        imageUrl: "https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg",
    };
</script>

<div class="flex flex-col flex-1 w-full h-full max-w-screen-md gap-8 px-8 py-8">
    <h1 class="text-4xl font-bold">About</h1>
    <p class="text-lg">
        This is a simple web application about the “Trolley Problem” meme, where
        users can set up their own morally difficult scenarios.
    </p>

    <div class="flex justify-center">
        <figure class="w-full max-w-screen-sm">
            <Diagram
                leftVictims={[person, person, person, person, person]}
                rightVictims={[person]}
                rightLabel={"Your best friend"}
                leftLabel=""
            />

            <figcaption class="text-sm text-center text-gray-500">
                A typical Trolley Problem scenario
            </figcaption>
        </figure>
    </div>

    <h2 class="text-2xl font-bold">How to use</h2>

    <p>
        To create a new scenario, click on the “Create new” button on the home
        page. You can add as many victims as you want to the left and right side
        of the track. You can also add labels to the left and right side of the
        track.
    </p>

    <p>
        Once you have set up your scenario, click on the “Submit” button to
        submit your scenario. You will be redirected to the home page.
    </p>

    <p>
        To view a scenario, click on a category button on the home page. You can
        view the details of the scenario and see the victims and labels you have
        added.
    </p>
    <p>
        Once you decided whether to pull the lever or not, a summary will appear
        of what the community has decided as well as a comment section to
        discuss the scenario.
    </p>

    <h2 class="text-2xl font-bold">Features</h2>

    <ul class="pl-8 list-disc">
        <li>View a list of categories</li>
        <li>View a list of scenarios in a category</li>
        <li>View the details of a scenario</li>
        <li>View the summary of a scenario</li>
        <li>View the comments of a scenario</li>
        <li>Submit a new scenario</li>
        <li>Discuss a scenario</li>
    </ul>

    <h2 class="text-2xl font-bold">Technologies used</h2>

    <ul class="pl-8 list-disc">
        <li>Svelte</li>
        <li>Typescript</li>
        <li>Tailwind CSS</li>
        <li>Java Spring Boot</li>
    </ul>

    <h2 class="text-2xl font-bold">Authors</h2>

    <p class="flex flex-col gap-2">
        <span>Livio Brunner</span>
        <figure class="flex flex-col gap-2 jusitfy-center">
            <EndlessYouPullAnimation class="w-full max-w-16" />
            <figcaption class="text-sm text-gray-500">(That's me)</figcaption>
        </figure>
    </p>
</div>
