package uk.co.ribot.androidboilerplate.injection.component;


import dagger.Component;
import uk.co.ribot.androidboilerplate.injection.PerFragment;
import uk.co.ribot.androidboilerplate.injection.module.FragmentModule;

/**
 * Created by Max Chervatiuk on 31.03.16.
 * Email: duo.blood@gmail.com
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
