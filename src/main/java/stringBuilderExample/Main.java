package stringBuilderExample;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder sb = new CustomStringBuilder();
        sb.append("A")
                .append("B")
                .append("C");
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
    }
}
