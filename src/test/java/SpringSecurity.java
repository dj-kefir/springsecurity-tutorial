/**
 * Created by ozol on 30.09.2016.
 */
//public class SpringSecurity {
//
//    private static AuthenticationManager am = new SampleAuthenticationManager();
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        while (true) {
//            System.out.println("Please enter your username:");
//            String name = in.readLine();
//            System.out.println("Please enter your password:");
//            String password = in.readLine();
//            try {
//                Authentication request = new UsernamePasswordAuthenticationToken(name, password);
//                Authentication result = am.authenticate(request);
//                SecurityContextHolder.getContext().setAuthentication(result);
//                break;
//            } catch (AuthenticationException e) {
//                System.out.println("Authentication failed: " + e.getMessage());
//            }
//        }
//        System.out.println("Successfully authenticated. Security context contains: " +
//                SecurityContextHolder.getContext().getAuthentication());
//    }
//
//    static class SampleAuthenticationManager implements AuthenticationManager {
//        static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
//
//        static {
//            AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//
//        public Authentication authenticate(Authentication auth) throws AuthenticationException {
//            if (auth.getName().equals(auth.getCredentials())) {
//                return new UsernamePasswordAuthenticationToken(auth.getName(),
//                        auth.getCredentials(), AUTHORITIES);
//            }
//            throw new BadCredentialsException("Bad Credentials");
//        }
//    }
//}
