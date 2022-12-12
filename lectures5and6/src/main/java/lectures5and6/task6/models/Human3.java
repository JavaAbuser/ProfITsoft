package lectures5and6.task6.models;

import java.util.Objects;

public class Human3 {
    private String name;
    private boolean isMan;

    public Human3() {
    }

    public Human3(String name, boolean isMan) {
        this.name = name;
        this.isMan = isMan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human3)) return false;
        Human3 human3 = (Human3) o;
        return isMan == human3.isMan && name.equals(human3.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isMan);
    }
}
