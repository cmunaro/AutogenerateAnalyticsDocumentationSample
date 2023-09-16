import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        DependenciesProviderHelper.shared.doInitKoin()
    }
    
    @StateObject var viewModel = HomeViewModel()

    var body: some Scene {
        WindowGroup {
            HomeScreen(
                state: viewModel.homeState,
                onAdd1000Dollars: viewModel.add1000Dollars,
                onSubtract1000Dollars: viewModel.subtract1000Dollars
            )
        }
    }
}
