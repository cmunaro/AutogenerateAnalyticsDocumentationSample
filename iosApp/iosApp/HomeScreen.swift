import SwiftUI

struct HomeScreen: View {
    let state: HomeState
    let onAdd1000Dollars: () -> Void
    let onSubtract1000Dollars: () -> Void

    var body: some View {
        ZStack {
            Color(.systemBackground)
                .ignoresSafeArea()

            VStack {
                Text("Account balance: \(String(format: "%.2f", state.accountBalance ?? 0))")
                    .font(.headline)

                Spacer()
                    .frame(height: 20)

                Button(action: onAdd1000Dollars) {
                    Text("Add $1000")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }

                Button(action: onSubtract1000Dollars) {
                    Text("Subtract $1000")
                        .padding()
                        .background(Color.red)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
            }
            .padding()
        }
    }
}
