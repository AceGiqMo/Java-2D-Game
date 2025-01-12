package gameworld.objects;

import gameworld.Component;

public abstract class GameObject extends Component {

    protected ObjectState state;

    public final void setState(ObjectState state) {
        this.state = state;
    }
}
