package app.db.models;

public abstract class Model {
    public abstract Integer getId();

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj.getClass() == this.getClass()
                && ((Model) obj).getId() != null
                && ((Model) obj).getId().equals(this.getId());
    }
}
