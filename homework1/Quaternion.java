import java.util.List;

public record Quaternion(double a, double b, double c, double d) {

    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);
    public static final Quaternion I = new Quaternion(0, 1, 0, 0);
    public static final Quaternion J = new Quaternion(0, 0, 1, 0);
    public static final Quaternion K = new Quaternion(0, 0, 0, 1);

    public Quaternion {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c) || Double.isNaN(d)) {
          throw new IllegalArgumentException("Fields cannot be NaN");
      }
    }

    public Quaternion plus(Quaternion qt) {
        return new Quaternion(a + qt.a, b + qt.b, c + qt.c, d + qt.d);
    }

    public Quaternion minus(Quaternion qt) {
       return new Quaternion(a - qt.a, b - qt.b, c - qt.c, d - qt.d);
    }

    public Quaternion times(double k) {
        return new Quaternion(k * a, k * b, k * c, k * d);
    }
    public double norm() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }
    public Quaternion normalized() {
        double n = norm();
        return new Quaternion(a / n, b / n, c / n, d / n);
    }
    public Quaternion conjugate() {
        return new Quaternion(a, -b, -c, -d);
    }
    public Quaternion inverse() {
        Quaternion conjugate = this.conjugate();
        double normSquared = a * a + b * b + c * c + d * d;
        return new Quaternion(conjugate.a / normSquared, conjugate.b / normSquared, conjugate.c / normSquared, conjugate.d / normSquared);
    }
    public List<Double> coefficients() {
        return List.of(a, b, c, d);
    }    
}

