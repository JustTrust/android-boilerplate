package uk.co.ribot.androidboilerplate.injection.component;


import dagger.Component;
import uk.co.ribot.androidboilerplate.injection.PerFragment;
import uk.co.ribot.androidboilerplate.injection.module.FragmentModule;


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
